import React from 'react';
import {List, Avatar} from 'antd';

export default function BarrageShowList(props) {
  return (
    <List
      dataSource={props.data}
      renderItem={
        item => (
          <List.Item>
            <List.Item.Meta 
              avatar={<Avatar src={`https://apic.douyucdn.cn/upload/${item.avatarUrl}_middle.jpg`} />}
              title={<div>{item.username}</div>}
              description={item.barrage}
            />
          </List.Item>
        )
      }
    />
  );
}