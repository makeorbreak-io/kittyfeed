import RPi.GPIO as GPIO
from time import sleep

GPIO.setmode(GPIO.BCM)
GPIO.setup(18,GPIO.OUT)
pwm=GPIO.PWM(18,50)
pwm.start(0)

GPIO.output(18, True)
pwm.ChangeDutyCycle(6.2)  #clockwise
sleep(0.45)

pwm.ChangeDutyCycle(52)	  #counterclockwise
sleep(0.6)
GPIO.output(18, False)

pwm.stop()
GPIO.cleanup()
