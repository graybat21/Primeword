from rest_framework import serializers
from users.models import UserVO, AuthVO

class UsersSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserVO
        # fields = '__all__'
        fields = ('id', 'sid', 'social', 'regdate',)

class AuthsSerializer(serializers.ModelSerializer):
    class Meta:
        model = AuthVO
        fields = ('access_token','social',)