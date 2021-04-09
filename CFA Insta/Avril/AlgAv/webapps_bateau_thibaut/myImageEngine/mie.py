import utils as u
from fastapi import FastAPI
from myImageEngine.routers import imageFromString as ifs
from myImageEngine.data.models import Image, Tags, Tagmap, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy import exc

app = FastAPI()

db_string = u.DB_MIE_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)


def initDB():
    # Using readlines()
    file1 = open('url.txt', 'r')
    Lines = file1.readlines()
    tagList = []
    tagmapList = []

    # Ajout des URL
    for url in Lines:
        # Génération des tags
        session.add(Image(url=url))
        tags = ((url.split('/')[len(url.split('/'))-1]).split('_')[0]).split('-')
        tagmap = {"url": url, "tags_object": []}
        for tag in tags:
            tagmap["tags_object"].append(tag)
            if tag not in [i.value for i in tagList]:
                tagList.append(Tags(value=tag))
        tagmapList.append(tagmap)
        session.add_all(tagList)
    session.commit()

    for tagmap in tagmapList:
        url_id = ((session.query(Image).filter(Image.url == tagmap["url"])).first()).pk
        print("\n\n========\n\n")
        for item in tagmap["tags_object"]:
            print(item)
            tag_id = ((session.query(Tags).filter(Tags.value == item)).first()).pk
            now_in_db = session.query(Tagmap).filter(Tagmap.pk_image == url_id, Tagmap.pk_tag == tag_id).all()
            if not now_in_db:
                session.add(Tagmap(pk_image=url_id, pk_tag=tag_id))
        session.commit()

app.include_router(ifs.router)


@app.get("/helloworld")
def read_root():
    initDB()
    return {"Hello": "World"}
