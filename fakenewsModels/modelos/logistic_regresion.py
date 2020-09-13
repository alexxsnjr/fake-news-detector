#Modelo de regresion logistica
import pandas as pd
import re
from nltk.stem.porter import PorterStemmer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
import pickle
from sklearn.linear_model import LogisticRegressionCV
from sklearn.pipeline import make_pipeline


df = pd.read_csv('../Datos/fakes1000.csv')

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
X = df['Text']
y = df['class'].values

#Valores de entrenamiento
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=0, test_size=0.5, shuffle=False)

#Modello
lr = LogisticRegressionCV(cv=5, scoring='accuracy', random_state=0, n_jobs=-1, verbose=3, max_iter=300)

#He creado una pipeline para que no haya que aplicar a los datos el TF-IDF vectorization, sino que al pasarlos al modelo se vectoricen solos.
clf=(make_pipeline(tfidf, lr))

#CLF es el nombre del modelo, y .fit lo que hace es entrenarlo (X_train son las noticias, (text de la base de datos). y_train son el true o false (class de la base de datos) )
clf.fit(X_train, y_train)

#Guardar el modelo
fake_news_model = open('fake_news_model.sav', 'wb')
pickle.dump(clf, fake_news_model)
fake_news_model.close()

#########################################################################################################################################################
#########################################################################################################################################################
#
# El modelo y el entrenamiento acaban en la linea 57.
#
# Lo que viene a continuacion se puede hacer en un documento a parte y solo tienes que importar pickle de la siguiente forma:
#
#    import pickel
#
#########################################################################################################################################################
#########################################################################################################################################################

#import pickle (no hace falta volver a importarlo porque lo he importado al principio)

#saved_clf --> Nombre del objeto (modelo), lo que se hace es cargar el objeto que esta almacenado en el archivo 'fake_news_model.sav'
saved_clf = pickle.load(open('fake_news_model.sav', 'rb'))

#saved_clf.predict(['LO QUE SE QUIERA PREDECIR']) --> lo que nos devuelve es True o False
print(saved_clf.predict(['Las pruebas realizadas en el Centro Nacional de Microbiología (CNM) del Instituto de Salud Carlos III han confirmado que el paciente ingresado en el Hospital de Cruces de Barakaldo (Bizkaia) sufre la rabia, según han confirmado a EL PAÍS fuentes sanitar']))

#saved_clf.score(X_test = cosas para predecir, y_test = respuestas correctas) --> LO que nos da es el porcentaje de predicciones correctas que ha hecho
print(saved_clf.score(X_test, y_test))