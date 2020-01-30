import React from 'react';
import {Row, Col} from 'antd';

import ChartArea from './ChartArea';
import BarrageShowList from './BarrageShowList';

export default function MainArea() {
  return(
    <Row>
      <Col span={16}>
        <ChartArea />
      </Col>
      <Col span={8}>
        <BarrageShowList />
      </Col>
    </Row>
  )
}