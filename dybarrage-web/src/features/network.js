import axios from 'axios';

const axiosIns = axios.create({
  baseURL: 'api/'
});

export const getRoomBaseInfo = (roomId) => {
  return axiosIns.get(`room/${roomId}/roombaseinfo`);
}

export const getSystemBaseInfo = (roomId) => {
  return axiosIns.get(`room/${roomId}/sysbaseinfo`);
}

export const getChartData = (roomId) => {
  return axiosIns.get(`room/${roomId}/chartdata`);
}

export const getBarrageData = (roomId) => {
  return axiosIns.get(`room/${roomId}/barragedata?num=15`);
}

export const crawlHeartbeat = (roomId) => {
  return axiosIns.get(`room/${roomId}/crawl`);
}