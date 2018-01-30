from django.shortcuts import render

# Create your views here.
from rest_framework.generics import ListCreateAPIView, RetrieveUpdateDestroyAPIView

from words.models import WordVO, NoteVO, TextbookVO
from words.serializer import WordsSerializer, NotesSerializer, TextbooksSerializer


class WordsList(ListCreateAPIView):
    """
    get: 전체 단어 보기
    post: 단어 추가
    """
    queryset = WordVO.objects.all()
    serializer_class = WordsSerializer


class WordsDetail(RetrieveUpdateDestroyAPIView):
    """
    get: 단어하나 요청
    put: 단어 수정
    delete: 단어 삭제
    """
    queryset = WordVO.objects.all()
    serializer_class = WordsSerializer


class NotesList(ListCreateAPIView):
    """
    get: 전체 단어 보기
    post: 단어 추가
    """
    queryset = NoteVO.objects.all()
    serializer_class = NotesSerializer


class NotesDetail(RetrieveUpdateDestroyAPIView):
    """
    get: 단어하나 요청
    put: 단어 수정
    delete: 단어 삭제
    """
    queryset = NoteVO.objects.all()
    serializer_class = NotesSerializer


class TextbooksList(ListCreateAPIView):
    """
    get: 전체 단어 보기
    post: 단어 추가
    """
    queryset = TextbookVO.objects.all()
    serializer_class = TextbooksSerializer


class TextbooksDetail(RetrieveUpdateDestroyAPIView):
    """
    get: 단어하나 요청
    put: 단어 수정
    delete: 단어 삭제
    """
    queryset = TextbookVO.objects.all()
    serializer_class = TextbooksSerializer
