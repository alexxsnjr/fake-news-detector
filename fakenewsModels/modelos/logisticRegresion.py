import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
import pickle
from sklearn.linear_model import LogisticRegressionCV
from sklearn.pipeline import make_pipeline
from nltk.corpus import stopwords

spanish_stopwords = stopwords.words('spanish')


df = pd.read_csv('../Datos/test.csv')[['Class', 'Text']]


def tokenizer_porter(text):
    return text.split()

#TF-IDF
tfidf = TfidfVectorizer(strip_accents=None,
                        lowercase=False,
                        preprocessor=None,
                        tokenizer=tokenizer_porter,
                        use_idf=True,
                        norm='l2',
                        smooth_idf=True,
                        stop_words = spanish_stopwords)
X = df['Text']
y = df['Class'].values

X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=2, test_size=0.5, shuffle=False)

lr = LogisticRegressionCV(cv=5, scoring='accuracy', random_state=2, n_jobs=-1, verbose=3, max_iter=300)

clf=(make_pipeline(tfidf, lr))

clf.fit(X_train, y_train)

LogReg = open('LogReg.sav', 'wb')
pickle.dump(clf, LogReg)
LogReg.close()


# print (clf.score(X_test, y_test))
# 0.8024691358024691