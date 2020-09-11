#Modelo de regresion logistica
import pandas as pd
import re
from nltk.stem.porter import PorterStemmer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
import pickle
from sklearn.linear_model import LogisticRegressionCV

df = pd.read_csv('../Datos/fakes1000.csv')

#TODO: Eliminar valores repetidos de la base de datos
def preprocessor(text):
    
    text = re.sub('<[^>]*>', '', text)
    text = re.sub(r'[^\w\s]','', text)
    text = text.lower()
    return text

#Eliminamos mayusculas y minusculas del dataset:
df['Text'].apply(preprocessor)


#Tokenizar
porter = PorterStemmer()
def tokenizer_porter(text):
    return [porter.stem(word) for word in text.split()]

#TF-IDF
tfidf = TfidfVectorizer(strip_accents=None,
                        lowercase=False,
                        preprocessor=None,
                        tokenizer=tokenizer_porter,
                        use_idf=True,
                        norm='l2',
                        smooth_idf=True)
X = tfidf.fit_transform(df['Text'])
y = df['class'].values

#Valores de entrenamiento
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=0, test_size=0.5, shuffle=False)

#Modello
clf = LogisticRegressionCV(cv=5, scoring='accuracy', random_state=0, n_jobs=-1, verbose=3, max_iter=300).fit(X_train, y_train)

#Guardar el modelo
fake_news_model = open('fake_news_model.sav', 'wb')
pickle.dump(clf, fake_news_model)
fake_news_model.close()

#Probar la eficacia del modelo
filename = 'fake_news_model.sav'
saved_clf = pickle.load(open(filename, 'rb'))

print(saved_clf.score(X_test, y_test))