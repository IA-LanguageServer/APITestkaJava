package api_testka.utils.process;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class OpenDriverProcess extends Thread {

    // AutoControl Driver path
    private String driverPath;
    // Process use to open driver
    private Process process;
    // use to create process
    private List<String> processCommandList;

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
                    processBuilder.directory(new File(Path.of("").toAbsolutePath().toString()));
                    processBuilder.inheritIO();
                } else {
                    processBuilder = new ProcessBuilder(this.processCommandList);
                    processBuilder.directory(new File(Path.of("").toAbsolutePath().toString()));
                    processBuilder.inheritIO();
                }
                this.process = processBuilder.start();
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
