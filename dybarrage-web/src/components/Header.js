import React from 'react';

import RoomBaseInfo from './RoomBaseInfo';
import SystemBaseInfo from './SystemBaseInfo';

export default function Header(props) {
  return (
    <RoomBaseInfo>
      <SystemBaseInfo />
    </RoomBaseInfo>
  );
}