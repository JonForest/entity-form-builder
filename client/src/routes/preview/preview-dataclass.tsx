import { ENTITIES_JSON_KEY } from '../../constants';
import Preview from '../../components/route-components/preview';

const url = 'http://localhost:8080/api/preview/dataclass';

export default function PreviewDataclass() {
  return (
    <>
      <h1>Data class generated</h1>
      <Preview storageKey={ENTITIES_JSON_KEY} url={url} />
    </>
  );
}
