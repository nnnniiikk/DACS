package neuronet.copy;

import com.irvil.textclassifier.model.VocabularyWord;

import java.util.List;

public interface VocabularyWordDAO {
  List<VocabularyWord> getAll();

  void addAll(List<VocabularyWord> vocabulary) throws AlreadyExistsException;
}
