#include <wiringPi.h>
//#include <softPwm.h>

int main()
{
	wiringPiSetup();
	
	pinMode(0, OUTPUT);
	pinMode(1, OUTPUT);
			
	digitalWrite(0, HIGH);
	digitalWrite(1, LOW);
	
	delay(1000);
	
	digitalWrite(0, LOW);
	digitalWrite(1, LOW);	
	
	return 0;
}
