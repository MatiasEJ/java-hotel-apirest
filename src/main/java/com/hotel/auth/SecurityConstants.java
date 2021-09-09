package com.hotel.auth;


import com.hotel.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String RSA_PRIVADA   ="-----BEGIN RSA PRIVATE KEY-----\n" +
        "MIIEpQIBAAKCAQEA3lLs6Eq7WoUNm0kLtwz9YAyhBZ1QohvaPqXgzsyO7R7/Peaa\n" +
        "IZUqcacg44myUBQp7ShEt30mw+nSWdQoaPlxAt58Cp8WmaPS742p/jQUlKf3gwmZ\n" +
        "NRJSGFrzd3Qzuz/h4o0U6dpzLPFPbTQHoSFMQ+wrm5j9MEBwtQqjDyMnoNmphEGn\n" +
        "TLHbotP+H3oo20eFvdkHdz9PtYQAPdWJ7+AlXj/RUczF76WjziDH3ia/eQu60Cti\n" +
        "LGJwDk+WenukMYGbIJ0zyzyc9dBq99jY3FIgRbeVEoLsaHXuW6sIxI1+WeXxUzZ5\n" +
        "bvhUJ4XtGUGLACRmJDVEdvqv1gnQhXdqxkz52QIDAQABAoIBAQDJu39CbIVCZtbo\n" +
        "38gkikHjBLKbkJqY7iMDk6rAmzbZjLXYKkYTUPHZfOhH7ysApLtCebaYtJQXUzTO\n" +
        "08bzMdxM2Sq+zIpKUS/WtLnactF17k+V9ICzenNJrEWdT7CdKSNDWfbvF4xI7JHA\n" +
        "qmR93eQBAeyz3IdpYB+k9wjsdF7CAO2f5NdxkOyhdV7sniYnMuFUmD6YzuaBhpCc\n" +
        "fCSrAu5EyTspEbBWrzVXldjL7icdPL54MQY03OZIbcMM9sir62WHlRA3dNKzZLHE\n" +
        "aW0QbqhvUG7oDy5zsLvKPFEKEjxVilOlZhoat/VYZPluPSKLsoFnG4TcuK23yGyd\n" +
        "JhyAH3mxAoGBAP2kyVRMObBXeVL3DbWtpvJwAgJAqnUoYabtCOemXs0TUVTEAep6\n" +
        "HbNQGPgX4C5uRduTXstrvW0Oj+gp9k9Sgr6jjqIk5l8+bZEFFblG09fS5nPz/K7I\n" +
        "8ceS9wJfgrHiWQr5urinag9QtOWvHHCy8Uie4wA70dUSPsQUmrDypqMlAoGBAOBj\n" +
        "p49/OLfdfRSTmSuYPyywsgO5NjaXvKLfler6qZ7TVs2mAP1fHyHLApRa1X5f01xN\n" +
        "0ZOY9iMYUiurm+33wur1F6lqN/oV1qPJq8e1XjdgF1Qc8bBkfIwFzWWdATtAHzFV\n" +
        "aGISght1daqo/5ugRFfXTwVQVmCHfUh0AG2Rj5elAoGBAK19uIQje1YmDwNznvoE\n" +
        "5Gtxt74Is09dpqXB/lRM1Q1ayEe9pj3W91e+UsXnttyrarcNL0rez/u3eTFo+mQ5\n" +
        "YNq7DwXTTYwaLGGEY96OFrrKM/1eBxkas146JSCHaeL4msVKGbDSOlZtcr6kL0Ta\n" +
        "8PUmUMjJIfam+y2tlj2wS9DNAoGAa19ETuYRvem+cJowMSwpDrl+dUmrJ3R0T3ut\n" +
        "wUMw5085ui41Swi5XPJHybWtc0tnWEPkhTwsi56Xs03qz3Q/US72o5qwLntEuhtG\n" +
        "l8mxy82vXBB9lmp/LoGYR4dZj4CfbHvx9vvkRj9ujrtPlKicC2x7448NxiMPUBwy\n" +
        "GDwO+gECgYEArpwsjmZDqmitchUH+47DUcm31YvkG0lT0YOFeoo0vzPzVB815RYM\n" +
        "xzqksP/8y/P2joCbzUwn5vO077tMi60YEqadTz4JiaVaWRyZcaOjUvcaCu8jTWcZ\n" +
        "gJt6O0rwkRZ6TXQxbYkYB1PxRkJ3ZoUrlphHocdN3TknHi9zmDWh45k=\n" +
        "-----END RSA PRIVATE KEY-----" ;
    public static final String RSA_PUBLICA="-----BEGIN PUBLIC KEY-----\n" +
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3lLs6Eq7WoUNm0kLtwz9\n" +
        "YAyhBZ1QohvaPqXgzsyO7R7/PeaaIZUqcacg44myUBQp7ShEt30mw+nSWdQoaPlx\n" +
        "At58Cp8WmaPS742p/jQUlKf3gwmZNRJSGFrzd3Qzuz/h4o0U6dpzLPFPbTQHoSFM\n" +
        "Q+wrm5j9MEBwtQqjDyMnoNmphEGnTLHbotP+H3oo20eFvdkHdz9PtYQAPdWJ7+Al\n" +
        "Xj/RUczF76WjziDH3ia/eQu60CtiLGJwDk+WenukMYGbIJ0zyzyc9dBq99jY3FIg\n" +
        "RbeVEoLsaHXuW6sIxI1+WeXxUzZ5bvhUJ4XtGUGLACRmJDVEdvqv1gnQhXdqxkz5\n" +
        "2QIDAQAB\n" +
        "-----END PUBLIC KEY-----" ;
    
    
    
}
