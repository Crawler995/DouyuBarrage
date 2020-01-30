import React from 'react';
import './App.css';

import Header from './components/Header';
import MainArea from './components/MainArea';
import {getRoomIdFromUrl} from './features/util';
import {crawlHeartbeat} from './features/network';
import {CRAWL_HEARTBEAT_INTERVAL} from './features/constants';


export default class App extends React.Component {
  constructor() {
    super();
    this.roomId = getRoomIdFromUrl();
  }

  crawlHeartbeat() {
    crawlHeartbeat(this.roomId);
    setInterval(() => {
      crawlHeartbeat(this.roomId);
    }, CRAWL_HEARTBEAT_INTERVAL);
  }

  componentDidMount() {
    if(this.roomId !== null) {
      this.crawlHeartbeat();
    }
  }

  render() {
    if(this.roomId === null) {
      return <div></div>;
    }
    
    return (
      <div className="App">
        <Header />

        <MainArea />
      </div>
    );
  }
}
