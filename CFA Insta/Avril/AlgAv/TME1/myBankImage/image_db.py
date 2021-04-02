from myBankImage.models import Image, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

db_string = "sqlite:///myBankImage/imageDB.db"
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)

session.commit()


def initDB():
    # Using readlines()
    file1 = open('url.txt', 'r')
    Lines = file1.readlines()

    for line in Lines:
        pic = Image(url = line.strip())
        session.add(pic)
        try:
            session.commit()
        except :
            pass


def iurlById(id):
    resdb = session.query(Image).filter(Image.pk == id)
    return resdb.first()


def randImage():
    import random
    rand = random.randrange(0, session.query(Image).count())
    row = session.query(Image)[rand]
    return row
