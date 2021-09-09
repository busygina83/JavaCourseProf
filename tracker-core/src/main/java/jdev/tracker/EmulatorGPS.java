package jdev.tracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.Point;
import static java.lang.Math.PI;

public class EmulatorGPS {

    //создадим новый объект

    //установим начальные значения GPS трекинга грузовика
    public void setBegin(Point point, String AutoId) {
        point.setAutoId(AutoId);
        point.setTime(System.currentTimeMillis());
        point.setLat(Math.random()*180-90);
        point.setLon(Math.random()*360-180);
        point.setAzimuth(Math.random()*360);
        point.setAzimuthDelta(0);
        point.setDuration(0);
        point.setSpeed(Math.random() * 10 + 20);
        point.setSpeedUp(0);
        System.out.println("setBegin:   "+point.toObject());
    }

    public String setEnd(Point point, String AutoId, long timeout) throws JsonProcessingException, InterruptedException {

//        пределы значений: широта от −90° до +90°, долгота от −180° до +180°
//        максимальная скорость 108 км/ч
//        максимальное ускорение и торможение 1 м/с2
//        маскимальный угол поворота за 1 сек 20 градусов от текущего направления

        //проверяем что наш объект совпадает с AutoId
        String lastAutoId=point.getAutoId();
        if (lastAutoId != null && !lastAutoId.equals(AutoId)){
            setBegin(point, "123");
            Thread.sleep(timeout);
        }
        else {
            //сгенерируем произвольное ускорение или торможение в пределах +-1м/с2
            double curSpeedUp = Math.random() * 2 - 1;
            //присвоим параметрам предыдущие значения
            double lastSpeed = point.getSpeed();
            long lastTime = point.getTime();
            //найдем текущее время и найдем разницу с предыдущим в секундах
            long curTime = System.currentTimeMillis();
            double deltaTime = (double) ((curTime - lastTime) / 1000);
            //найдем текущую скорость в пределах 72..108 км/ч или 20..30 м/с и скорекритуем ускорение
            double curSpeed = lastSpeed + (curSpeedUp * deltaTime);
            if (curSpeed < 20) {
                curSpeed = 20;
            }
            if (curSpeed > 30) {
                curSpeed = 30;
            }
            curSpeedUp = (curSpeed - lastSpeed) / deltaTime;
            //найдем пройденное расстояние в метрах
            double curDist = ((lastSpeed * deltaTime) + (curSpeedUp * Math.pow(deltaTime, 2)) / 2);
            //установим новый азимут учетом отклонения от текущей траектории +-20 град за сек
            double lastAzimuth = point.getAzimuth();
            double lastAzimuthDelta = point.getAzimuthDelta();
            double lastDuration = point.getDuration();
            double curAzimuthDelta;
            double curDuration;
            //если текущий участок дороги (прямая или поворот) завершился, начинаем следующий участок
            if (lastDuration == 0) {
                //если завершилась прямая, конструируем весь участок поворота
                if (lastAzimuthDelta == 0) {
                    //генерируем угол уклона от -2..-5 до +2..+5 градусов в секунду
                    curAzimuthDelta = (Math.random() * 3 + 2) * (double)((int)(Math.random() * 2) * 2 - 1);
                    //генерируем общий поворот на 20..120 градусов
                    curDuration = Math.random() * 100 + 20;
                }
                //иначе конструируем прямой участок дороги
                else {
                    //текущего отклонения от азимута не будет
                    curAzimuthDelta = 0;
                    //генерируем общую дину текущего прямого участка
                    curDuration = Math.random() * 1000 + 500;
                }
            }
            //если грузовик еще движется по сконструируемому участку дороги
            else {
                curAzimuthDelta = lastAzimuthDelta;
                //если движется по прямой
                if (curAzimuthDelta == 0) {
                    //определяем сколько метров от прямой части дороги осталось
                    curDuration = lastDuration - curSpeed * deltaTime;
                    if (curDuration < 0) {
                        curDuration = 0;
                    }
                }
                //если движется в повороте
                else {
                    //определяем сколько градусов от всего текущего поворота осталось
                    curDuration = lastDuration - Math.abs(curAzimuthDelta) * deltaTime;
                    if (curDuration < 0) {
                        curDuration = 0;
                    }
                }
            }
            //"подкручиваем" значения, если выходят за допустимые пределы
            //double curAzimuth = lastAzimuth + Math.abs(curAzimuthDelta);
            double curAzimuth = lastAzimuth + curAzimuthDelta;
            if (curAzimuth < 0) {
                curAzimuth = 360 + curAzimuth;
            }
            if (curAzimuth > 360) {
                curAzimuth = 360 - curAzimuth;
            }
            //найдем местоположение (широта и долгота) новой точки
            double lastLat = point.getLat();
            double lastLon = point.getLon();
            double curLat = lastLat + (Math.asin(Math.sin(lastLat * PI / 180) * Math.cos(curDist * PI / 180) + Math.cos(lastLat * PI / 180) * Math.sin(curDist * PI / 180) * Math.cos(curAzimuth * PI / 180)));
            double curLon = lastLon + (Math.atan(Math.sin(curDist * PI / 180) * Math.sin(curAzimuth * PI / 180) / (Math.cos(lastLat * PI / 180) * Math.cos(curDist * PI / 180) - Math.sin(lastLat * PI / 180) * Math.sin(curDist * PI / 180) * Math.cos(curAzimuth * PI / 180))));

            //присваиваем объекту новые параметры
            point.setAutoId(AutoId);
            point.setTime(curTime);
            point.setLat(curLat);
            point.setLon(curLon);
            point.setAzimuth(curAzimuth);
            point.setAzimuthDelta(curAzimuthDelta);
            point.setDuration(curDuration);
            point.setSpeed(curSpeed);
            point.setSpeedUp(curSpeedUp);
        }
//        System.out.println("point.toJson():   "+point.toJson());
        return point.toJson();
    }
}
