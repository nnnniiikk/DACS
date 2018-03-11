package neuronet.copy;

import java.io.File;

public interface StorageCreator {
  default void createStorageFolder(String path) {
    new File(path).mkdir();
  }

  void createStorage();
}