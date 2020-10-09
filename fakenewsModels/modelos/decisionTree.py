#Modelo de regresion logistica
import pandas as pd
import re
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
import pickle
from sklearn.ensemble import AdaBoostClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
from unidecode import unidecode
from nltk.corpus import stopwords

spanish_stopwords = stopwords.words('spanish')

#from sklearn.pipeline import make_pipeline

def tokenizer_porter(text):
    return text.split()

#TF-IDF
tfidf = TfidfVectorizer(strip_accents=None,
                        lowercase=False,
                        preprocessor=None,
                        tokenizer=tokenizer_porter,
                        use_idf=True,
                        norm='l2',
                        smooth_idf=True)

def preprocessor(text):
    
    text = re.sub('<[^>]*>', '', text)
    text = re.sub(r'[^\w\s]','', text)
    text = text.lower()
    text = re.sub('\d','', text)
    text = re.sub('number','', text)
    text = unidecode(text)
    return text


#df = pd.read_csv('../Datos/fakes1000.csv')
df = pd.read_csv('../Datos/test.csv')[['Class','Source','Text']]


x = df.loc[:,['Source','Text']]
x['source'] = x["Source"].astype(str) +" "+ x["Text"] 
x = x.drop(['Source','Text'],axis=1)

x = x.source.apply(preprocessor)


y = df.Class



x_train,x_test,y_train,y_test=train_test_split(x,y,test_size=0.30)

tfidf_vect = TfidfVectorizer(strip_accents=None,
                        lowercase=False,
                        preprocessor=None,
                        tokenizer=tokenizer_porter,
                        use_idf=True,
                        norm='l2',
                        smooth_idf=True,
                        stop_words = spanish_stopwords)

tfidf_train = tfidf_vect.fit_transform(x_train)
tfidf_test = tfidf_vect.transform(x_test)
tfidf_df = pd.DataFrame(tfidf_train.A, columns=tfidf_vect.get_feature_names())

Adab = AdaBoostClassifier(DecisionTreeClassifier(max_depth=10),n_estimators=5,random_state=1)
Adab.fit(tfidf_train, y_train)

y_pred3 = Adab.predict(tfidf_test)
ABscore = metrics.accuracy_score(y_test,y_pred3)
print("accuracy: %0.3f" %ABscore)

DecTree = open('DecTree.sav', 'wb')
pickle.dump(Adab, DecTree)
DecTree.close()

# Accuracy: 0.777