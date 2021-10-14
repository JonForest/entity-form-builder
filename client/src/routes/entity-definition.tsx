import JsonCapture from '../components/route-components/json-capture';
import { ENTITIES_JSON_KEY } from '../constants';

export default function EntityDefinition() {
  return (
    <>
      <h1>Entity definition</h1>
      <JsonCapture storageKey={ENTITIES_JSON_KEY} />
    </>
  );
}
