import React from 'react';
import './App.css';

import Header from './components/Header';
import MainArea from './components/MainArea';
import {getRoomIdFromUrl} from './features/util';

export default function App() {
  const roomId = getRoomIdFromUrl();
  if(roomId === null) {
    return <div></div>;
  }
  
  return (
    <div className="App">
      <Header />

      <MainArea />
    </div>
  );
}
