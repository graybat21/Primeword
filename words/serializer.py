from rest_framework import serializers
from words.models import WordVO, NoteVO, TextbookVO


class WordsSerializer(serializers.ModelSerializer):
    class Meta:
        model = WordVO
        fields = '__all__'

class NotesSerializer(serializers.ModelSerializer):
    class Meta:
        model = NoteVO
        fields = '__all__'

class TextbooksSerializer(serializers.ModelSerializer):
    class Meta:
        model = TextbookVO
        fields = '__all__'
