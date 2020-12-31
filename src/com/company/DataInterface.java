package com.company;

import java.io.IOException;

public interface DataInterface {
    void loadData(String filename) throws IOException;
    void saveData(String filename) throws IOException;
}
