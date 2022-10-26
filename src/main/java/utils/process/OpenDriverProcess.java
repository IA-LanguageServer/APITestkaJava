package utils.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OpenDriverProcess extends Thread {

    // AutoControl Driver path
    private String driverPath;
    // Process use to open driver
    private Process process;
    // use to create process
    private List<String> processCommandList;
    // use to read process output
    private InputStreamReader driverProcessReader;
    // use to read process error output
    private InputStreamReader driverErrorReader;

    public OpenDriverProcess(String driverPath) {
        this.driverPath = driverPath;
        this.setDaemon(true);
    }

    public OpenDriverProcess(List<String> processCommandList) {
        this.driverPath = processCommandList.get(0);
        this.processCommandList = processCommandList;
        this.setDaemon(true);
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getDriverPath() {
        return this.driverPath;
    }

    public void close() {
        if (this.process != null) {
            process.destroy();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            File checkDriver = new File(this.driverPath);
            if (checkDriver.exists()) {
                ProcessBuilder processBuilder;
                if (processCommandList == null ) {
                    processBuilder = new ProcessBuilder(this.driverPath);
                } else {
                    processBuilder = new ProcessBuilder(this.processCommandList);
                }
                this.process = processBuilder.start();
                driverProcessReader = new InputStreamReader(process.getInputStream());
                driverErrorReader = new InputStreamReader(process.getErrorStream());
                if (process != null) {
                    while (process.isAlive()) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
