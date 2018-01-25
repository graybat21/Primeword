from django.db import models
from django.contrib.auth.models import AbstractUser
import datetime

# Create your models here.

class UserVO(AbstractUser):
    academy = models.TextField(max_length=500, blank=True)
    school = models.TextField(max_length=500, blank=True)
    grade = models.IntegerField(default=1)
    name = models.TextField(max_length=500, blank=True)
