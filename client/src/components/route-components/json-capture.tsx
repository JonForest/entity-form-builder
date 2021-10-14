import { KeyboardEvent, useEffect, useState } from 'react';
import Button from '../common/button';

interface JsonCaptureInterface {
  storageKey: string;
}

export default function JsonCapture({ storageKey }: JsonCaptureInterface) {
  const [formJson, setFormJson] = useState('');
  const [isValid, setIsValid] = useState(false);

  useEffect(() => {
    // Retrieve any saved data from local storage
    const json = window.localStorage.getItem(storageKey);
    if (json) setFormJson(json);
  }, []);

  function preventTab(e: KeyboardEvent<HTMLTextAreaElement>) {
    if (e.key === 'Tab') {
      e.preventDefault();
    }
  }

  function saveJson(jsonString: string) {
    setFormJson(jsonString);
    try {
      JSON.parse(jsonString);
      setIsValid(true);
    } catch (e) {
      setIsValid(false);
    }
    window.localStorage.setItem(storageKey, jsonString);
  }

  function format() {
    let obj;
    try {
      obj = JSON.parse(formJson);
      setFormJson(JSON.stringify(obj, null, 2));
    } catch (e) {}
  }

  return (
    <>
      <p>Note: JSON will auto-save in local storage</p>
      <textarea
        className="json-capture"
        value={formJson}
        onKeyDown={(e) => preventTab(e)}
        onChange={(e) => saveJson(e.target.value)}
      />
      <div>
        <Button onClick={format} disabled={!isValid}>
          Format
        </Button>
      </div>
      <div>
        JSON is: <b>{isValid ? 'valid' : 'invalid'}</b>
      </div>
    </>
  );
}
