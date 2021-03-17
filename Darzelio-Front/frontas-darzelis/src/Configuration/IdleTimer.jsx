import React, { useRef } from 'react';
import IdleTimer from 'react-idle-timer';
import { useHistory } from 'react-router';

function IdleTimerContainer() {
  const idleTimerRef = useRef(null);
  const sessionTimeoutRef = useRef(null);
  let history = useHistory();

  const onIdle = () => {
    console.log('User is idle');
    sessionTimeoutRef.current = setTimeout(120 * 60 * 1000);
    history.push('/login');
  };

  return (
    <div>
      <IdleTimer
        ref={idleTimerRef}
        timeout={120 * 60 * 1000}
        data-toggle="modal"
        data-target={`#staticBackdrop`}
        onIdle={onIdle}
      />
    </div>
  );
}

export default IdleTimerContainer;
