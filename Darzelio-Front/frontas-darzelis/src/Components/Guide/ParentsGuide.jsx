import React from 'react';
import '../../Style/style.css';
import pdf from '../Guide/Tevai.pdf';

const Guide = () => {
  return (
    <div>
      {pdf ? (
        <iframe
          src={pdf}
          title="Naudojimosi instrukcija"
          style={{ marginRight: 0, marginLeft: 170, height: 800, width: 1200 }}
          allowFullScreen
        ></iframe>
      ) : (
        <div>...</div>
      )}
    </div>
  );
};
export default Guide;
