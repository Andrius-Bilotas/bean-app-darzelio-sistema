import React from 'react';
import '../../Style/style.css';
import pdf from '../Guide/Tevai.pdf';

const PublicGuide = () => {
  return (
    <div>
      {pdf ? (
        <iframe
          src={pdf}
          title="Naudojimosi instrukcija"
          style={{
            marginRight: 350,
            marginLeft: 350,
            height: 950,
            width: 1200,
          }}
          allowFullScreen
        ></iframe>
      ) : (
        <div>...</div>
      )}
    </div>
  );
};
export default PublicGuide;
