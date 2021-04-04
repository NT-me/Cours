from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, String, Integer, UniqueConstraint
Base = declarative_base()


class Image(Base):
    __tablename__ = "image"
    pk = Column(Integer, primary_key=True, autoincrement='ignore_fk')
    name = Column(String)
    url0 = Column(String)
    url1 = Column(String)
    url2 = Column(String)
    __table_args__ = (UniqueConstraint('pk'),)
