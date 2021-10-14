import apiRequest, { HTTPMethod } from '../../services/api-service';
import { ENTITIES_JSON_KEY } from '../../constants';
import { useEffect, useState } from 'react';

interface PreviewInterface {
  storageKey: string;
  url: string;
}

export default function Preview({ storageKey, url }: PreviewInterface) {
  const json = window.localStorage.getItem(storageKey);
  const [preview, setPreview] = useState('');

  useEffect(() => {
    async function fetchPreview() {
      try {
        const configuration = JSON.parse(json!);
        const data = await apiRequest(url, HTTPMethod.POST, {}, configuration);
        if (typeof data === 'string') setPreview(data);
        else alert(data.error);
      } catch (e) {
        alert('Unable to fetch data for configuration saved in local storage');
      }
    }

    if (json) {
      fetchPreview();
    }
  }, [json, setPreview, url]);

  if (!json) return <h1>No stored json</h1>;

  return (
    <>
      <p>From entity configuration found in local storage</p>
      <pre>{preview}</pre>
    </>
  );
}
