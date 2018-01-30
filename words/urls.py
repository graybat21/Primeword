"""greatchoice URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url

from words.views import WordsList, WordsDetail, NotesList, NotesDetail, TextbooksList, TextbooksDetail

urlpatterns = [
    url(r'^$', WordsList.as_view()),   
    url(r'^(?P<pk>[0-9]+)/$', WordsDetail.as_view()),
    url(r'^notes/$', NotesList.as_view()),
    url(r'^notes/(?P<pk>[0-9]+)/$', NotesDetail.as_view()),
    url(r'^textbooks/$', TextbooksList.as_view()),
    url(r'^textbooks/(?P<pk>[0-9]+)/$', TextbooksDetail.as_view()),
]