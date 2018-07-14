package com.ltp.spel.entity;

public class SystemPropertiesBean {

    private String osName;

    private String FileEncoding;

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getFileEncoding() {
        return FileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {
        FileEncoding = fileEncoding;
    }

    @Override
    public String toString() {
        return "SystemPropertiesBean{" +
                "osName='" + osName + '\'' +
                ", FileEncoding='" + FileEncoding + '\'' +
                '}';
    }
}
