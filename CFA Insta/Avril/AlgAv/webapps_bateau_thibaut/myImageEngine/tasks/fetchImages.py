import requests
import utils as u
from myImageEngine.data.models import Image
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from apscheduler.schedulers.background import BackgroundScheduler
import time

# sched = BackgroundScheduler()
db_string = u.DB_MIE_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()


def fetchImages():
    print("Fetch IMAGES from main server")
    r = requests.get(url=u.ADRESS_CANVA + "tig/products")
    pList = r.json()
    for item in pList:
        print(item)
        try:
            name = item["name"]
            url0 = requests.get(url="http://127.0.0.1:8000/mse/product/{0}/image/1?redirect=false".format(int(item["id"])))
            url1 = requests.get(url="http://127.0.0.1:8000/mse/product/{0}/image/2?redirect=false".format(int(item["id"])))
            url2 = requests.get(url="http://127.0.0.1:8000/mse/product/{0}/image/3?redirect=false".format(int(item["id"])))
            pr = Image(name=name, url0=url0.json()["url"], url1=url1.json()["url"], url2=url2.json()["url"])
            session.add(pr)
            session.commit()
        except :
            print("PROBLEM")
#
#
# sched.add_job(fetchImages, 'interval', minutes=2)
#
# sched.start()
