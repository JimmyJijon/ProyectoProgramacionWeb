import React from 'react';
import '../App.css';
import { ScrollPanel } from 'primereact/scrollpanel';

function VideoGallery() {
  return (
    
    <div className="video-page-wrapper">
      <ScrollPanel className="video-scroll-panel">
        <div className="video-container">
          <div className="video-row">
            <div className="video-column">
              <div className="video-item">
                <iframe
                  width="560"
                  height="315"
                  src="https://www.youtube.com/embed/zrFRgM0UEoY"
                  title="Video de inducción"
                  frameBorder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                ></iframe>
              </div>
              <div className="video-item">
                <iframe
                  width="560"
                  height="315"
                  src="https://www.youtube.com/embed/zzQ4qhRwrxA"
                  title="Otro video"
                  frameBorder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                ></iframe>
              </div>
            </div>

            <div className="video-column">
              <div className="video-item">
                <iframe
                  width="560"
                  height="315"
                  src="https://www.youtube.com/embed/aExC38vJRfk"
                  title="Video adicional 1"
                  frameBorder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                ></iframe>
              </div>
              <div className="video-item">
                <iframe
                  width="560"
                  height="315"
                  src="https://www.youtube.com/embed/VRzrnPRHJf4"
                  title="Video adicional 2"
                  frameBorder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowFullScreen
                ></iframe>
              </div>
            </div>
          </div>

          <div className="video-row">
            <div className="video-item">
              <iframe
                width="560"
                height="315"
                src="https://www.youtube.com/embed/1CQsepqMed4"
                title="Nuevo video 1"
                frameBorder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              ></iframe>
            </div>
            <div className="video-item">
              <iframe
                width="560"
                height="315"
                src="https://www.youtube.com/embed/LGs3itRoTrw"
                title="Nuevo video 2"
                frameBorder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              ></iframe>
            </div>
          </div>

          <div className="video-row">
            <div className="video-item">
              <iframe
                width="560"
                height="315"
                src="https://www.youtube.com/embed/hs22g-ghESo"
                title="Último video"
                frameBorder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowFullScreen
              ></iframe>
            </div>
          </div>
        </div>
      </ScrollPanel>
    </div>
  );
}

export default VideoGallery;