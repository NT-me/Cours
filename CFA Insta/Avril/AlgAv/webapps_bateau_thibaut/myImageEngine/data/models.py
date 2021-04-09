from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column, String, Integer, UniqueConstraint, ForeignKey
from sqlalchemy.orm import sessionmaker, relationship, backref
Base = declarative_base()


class Image(Base):
    __tablename__ = "image"
    pk = Column(Integer, primary_key=True, autoincrement='ignore_fk')
    url = Column(String)
    __table_args__ = (UniqueConstraint('pk'),)


class Tags(Base):
    __tablename__ = "tags"
    pk = Column(Integer, primary_key=True, autoincrement='ignore_fk')
    value = Column(String)
    __table_args__ = (UniqueConstraint('pk'),)


class Tagmap(Base):
    __tablename__ = "tagmap"
    pk = Column(Integer, primary_key=True, autoincrement='ignore_fk')
    pk_image = Column(Integer, ForeignKey('image.pk'))
    pk_tag = Column(Integer, ForeignKey('tags.pk'))
    __table_args__ = (UniqueConstraint('pk_image', 'pk_tag'),)
    # image = relationship('image', backref = 'Tagmap', lazy=True,cascade="all, delete-orphan")
    # tag = relationship('tag', backref = 'Tagmap', lazy=True,cascade="all, delete-orphan")
