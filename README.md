# AppIconSample

1、动态更新APP的图标    
2、显示图标右上角的数字（TODO）   

## 总结   
1、在使用上述的方法进行APP的图标更新，      
触发方法之后需要等待系统的Launcher刷新图标才可以显示效果来。      

2、不过在使用adb安装应用的时候，设备上APP图标必须和清单文件中的   
activity-alias中的android:enable=”true”的时候的图标情况保持一致，   
否则会报错。  
