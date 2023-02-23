package api_testka.utils.driver_manager;



import api_testka.utils.process.OpenDriverProcess;
import api_testka.bind.apt_test.APITest;
import api_testka.utils.socket.ClientSocket;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DriverManager {

    private ClientSocket clientSocket;
    private static OpenDriverProcess openDriverProcess;
    private String host;
    private int port;
    public String driverPath;
    public APITest apiTest = new APITest(this);

    /**
     * @param host: server host
     * @param port: server port
     * @param platform: which os we want to use [windows, linux, macos]
     * @return driverPath: driver full path
     */
    private String setDriver(String host, int port, String platform){
        switch (platform) {
            case "windows":
                if (this.driverPath == null)
                    this.driverPath = Path.of("").toAbsolutePath() + "/generate_apitestka_driver_win.exe";
                break;
            case "linux":
                if (this.driverPath == null)
                    this.driverPath = Path.of("").toAbsolutePath() + "/generate_apitestka_driver_linux";
                break;
            case "macos":
                if (driverPath == null)
                    this.driverPath = Path.of("").toAbsolutePath() + "/generate_apitestka_driver_macos";
                break;
        }
        this.host = host;
        this.port = port;
        this.driverPath = this.driverPath.replace("\\", "/");
        return this.driverPath;
    }

    /**
     * @param host: server host
     * @param port: server port
     * @param driverPath: which driver we want to use (need full path)
     * @param platform: which os we want to use [windows, linux, macos]
     * @throws IOException: If we can't start Driver Process
     */
    public DriverManager(String host, int port, String driverPath, String platform) throws IOException {
        this.driverPath = driverPath;
        setDriver(host, port, platform);
        if (openDriverProcess == null) {
            openDriverProcess = new OpenDriverProcess(this.driverPath);
            openDriverProcess.start();
            this.clientSocket = new ClientSocket(this.host, this.port);
            while (!openDriverProcess.isAlive()) {
            }
        } else {
            throw new IOException("Can't init DriverManager");
        }
    }
    /**
     * @param host: server host
     * @param port: server port
     * @param processCommandList: start process with command
     * @param platform: which os we want to use [windows, linux, macos]
     * @throws IOException: If we can't start Driver Process
     */
    public DriverManager(String host, int port, List<String> processCommandList, String platform) throws IOException {
        this.driverPath = processCommandList.get(0);
        setDriver(host, port, platform);
        if (openDriverProcess == null) {
            openDriverProcess = new OpenDriverProcess(processCommandList);
            openDriverProcess.start();
            this.clientSocket = new ClientSocket(this.host, this.port);
            while (!openDriverProcess.isAlive()) {
            }
        } else {
            throw new IOException("Can't init DriverManager");
        }
    }
    /**
     * @param commandToSend: use to send string command to server
     * @return server response string  if server doesn't response return ""
     */
    public String sendCommand(String commandToSend) {
        int retryCount = 5;
        while (retryCount >= 0) {
            if (openDriverProcess.isAlive() && this.clientSocket != null) {
                return this.clientSocket.sendData(commandToSend);
            } else {
                System.err.printf("Driver not ready %s%n", commandToSend);
                retryCount -= 1;
            }
        }
        return "";
    }
    /**
     * send quit_server to server and close all
     */
    public void quit() {
        try {
            this.clientSocket.sendData("quit_server");
            this.clientSocket.closeClient();
            openDriverProcess.close();
            openDriverProcess = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
