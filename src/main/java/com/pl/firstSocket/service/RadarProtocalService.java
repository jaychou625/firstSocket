package com.pl.firstSocket.service;

import com.pl.firstSocket.bean.TcpInfo;
import com.pl.firstSocket.bean.UltrasonicRadarProtocal;

public interface RadarProtocalService {
    public UltrasonicRadarProtocal getRadarInfo(TcpInfo tcpinfo);
}
