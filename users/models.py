from django.db import models
import datetime

# Create your models here.
class UserVO(models.Model):
    sid = models.CharField(max_length=255)
    social = models.CharField(max_length=50, default='NAVER')
    regdate = models.DateTimeField(auto_now_add=True)

    # 필요하면 칼럼 추가

    def __str__(self):
        return str(self.pk)

    def __int__(self):
        return self.pk

class AuthVO(models.Model):
    access_token = models.CharField(max_length=255)
    social = models.CharField(max_length=50, default='NAVER')
