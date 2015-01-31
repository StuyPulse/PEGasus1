## Documentations on Mechanisms to Control the Lift

### DESmond's Limit Switch

Model Number: AAP2T51Z11  
Product Page: http://www.automationdirect.com/adc/Shopping/Catalog/Sensors_-z-_Encoders/Limit_Switches/Double-Insulated_(PBT)_Limit_Switches_(AAP,_ABP_Series)/Side_Rotary_Adjustable_Lever_with_Polyamide_Roller_Actuator/AAP2T51Z11

Pros:

- Switch flips in both directions
- Builtin Roller
- Lever is adjustable length
- Will still work (somewhat) if dented

Cons:

- Can be dented

### DESiree's Limit Switch

Model Number: V7-2B17D8-048  
Product Page: http://sensing.honeywell.com/product-page?pr_id=29368

Pros:

- Pliable straight lever actuator

Cons:

- Straight lever actuator can be bent such that it no longer clicks the button


### JoeBot's Photogate

Model Number: QS18VN6D  
Product Number: 61651  
Product Page: http://www.bannerengineering.com/en-US/support/partref/61651

Specs (Look on product page for more details):

- Sensing Mode (General)    Proximity
- Sensing Mode (Specific)	Diffuse
- Sensing Beam	Infrared LED
- Max Sensing Range [mm]	450
- Supply Voltage	10-30 V dc 
- ...  




### Gyro/Accelerometers:

Model Number: ADXL 345  
Product Page: http://www.usfirst.org/sites/default/files/uploadedFiles/Robotics_Programs/FRC/Game_and_Season__Info/2012_Assets/Accelerometer-Gyro.pdf  
Order Page: http://www.andymark.com/product-p/am-2067.htm

Specs:

- Three Axis Accelerometer
- One Axis Gyroscope
- Separatable

Complaints:

- According to reviews, the gyroscope is very bad

## Documentations on Coding:

### Gyros:

Imports:

```java
import edu.wpi.first.wpilibj.Gyro;
```

Constructor:

```java
int analogChannelNumber = 1;
Gyro g = new Gyro(analogChannelNumber);
```

