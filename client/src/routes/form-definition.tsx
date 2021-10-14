import JsonCapture from '../components/route-components/json-capture';
import { FORMS_JSON_KEY } from '../constants';

export default function FormDefinition() {
  return (
    <>
      <h1>Form definition</h1>
      <JsonCapture storageKey={FORMS_JSON_KEY} />
    </>
  );
}
