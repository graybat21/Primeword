from rest_framework import serializers
from users.models import UserVO

class UsersSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserVO
        # fields = '__all__'
        fields = ('username', 'password', 'name', 'academy', 'school', 'grade')

# class AuthsSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = AuthVO
#         fields = ('access_token','social',)