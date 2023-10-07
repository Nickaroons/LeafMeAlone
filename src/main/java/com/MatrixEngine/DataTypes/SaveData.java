package com.MatrixEngine.DataTypes;

public class SaveData {
    String data;
    String name;

    public SaveData(String name, Object data) {
        this.data = String.valueOf(data);
        this.name = name;
    }

    public int toInt() {
        return Integer.valueOf(data);
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }

    public boolean toBool() {
        return Boolean.parseBoolean(data);
    }

    public double toDouble() {
        return Double.valueOf(data);
    }

    public String save() {
        return name + "-" + data;
    }

    public String getName() {
        return name;
    }
}
