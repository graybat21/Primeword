from django.db import models

# Create your models here.

class WordVO(models.Model):
    word = models.CharField(max_length=100)
    meaning = models.TextField(max_length=500)
    note = models.ForeignKey('NoteVO')

class NoteVO(models.Model):
    textbook = models.ForeignKey('TextbookVO')
    lesson = models.IntegerField(default=1)

class TextbookVO(models.Model):
    name = models.CharField(max_length=100)
    grade = models.CharField(max_length=20)
    user = models.ForeignKey('users.UserVO')