����   4 �  $com/iftac/projekt/kulturbotten/Tweet  java/lang/Object id I title Ljava/lang/String; description image Ljava/awt/image/BufferedImage; imageURL instance &Lcom/iftac/projekt/kulturbotten/Tweet; <init> ()V Code
     LineNumberTable LocalVariableTable this getInstance (()Lcom/iftac/projekt/kulturbotten/Tweet;	    
   StackMapTable toString ()Ljava/lang/String;	    
  " java/lang/StringBuilder $ 
Tweet [id=
 ! &  ' (Ljava/lang/String;)V	  )  
 ! + , - append (I)Ljava/lang/StringBuilder; / , title=
 ! 1 , 2 -(Ljava/lang/String;)Ljava/lang/StringBuilder;	  4   6 , description=	  8 	  : , image=
 ! < , = (Z)Ljava/lang/StringBuilder; ? , imageURL=	  A   C ]
 ! E   hasImage Z getDescription setDescription getPhoto  ()Ljava/awt/image/BufferedImage; M java/io/File O cachedImage.jpg
 L &
 R T S javax/imageio/ImageIO U V read .(Ljava/io/File;)Ljava/awt/image/BufferedImage;
 X Z Y java/io/IOException [  printStackTrace imageInJpeg e Ljava/io/IOException; ` java/awt/image/BufferedImage setPhoto ([B)V d java/io/ByteArrayInputStream
 c f  b
 R h U i 5(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage; k jpg
 R m n o write A(Ljava/awt/image/RenderedImage;Ljava/lang/String;Ljava/io/File;)Z	 q s r java/lang/System t u out Ljava/io/PrintStream;
 X w x  
getMessage
 z | { java/io/PrintStream } ' println b [B bImage imageInByte in Ljava/io/InputStream;  getTitle setTitle getID ()I setID (I)V getImageURL setImageURL 
SourceFile 
Tweet.java !                 	     
         
             /     *� �                        )       E      � � � Y� � � �              !  #                   �     Q<*� � <� !Y#� %*� (� *.� 0*� 3� 05� 0*� 7� 09� 0� ;>� 0*� @� 0B� 0� D�           )  + 	 ,  / < 0 M /        Q      O F G      �   H      /     *� 7�           4              I '     >     *+� 7�       
    8  9                	    J K     �     L� LYN� P� QL� M,� W+�     X         =  ?  @  B  E                \     ] ^      �    _  X  a b     �     :M+N� cY-� e:� gM,j� LYN� P� lW� :� p� v� y*,� �   $ ' X     .    J  K  Q  R  T  U   T $ W ) X 4 \ 9 ]    >    :       : ~    8 �    6 �     � �  )  ] ^      � '   � _ �  X  �      /     *� 3�           `              � '     >     *+� 3�       
    d  e                    � �     /     *� (�           h              � �     >     *� (�       
    l  m                    �      /     *� @�           p              � '     >     *+� @�       
    t  u                    �    �