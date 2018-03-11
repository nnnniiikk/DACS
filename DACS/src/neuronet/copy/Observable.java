package neuronet.copy;

public interface Observable {
  void addObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers(String text);
}