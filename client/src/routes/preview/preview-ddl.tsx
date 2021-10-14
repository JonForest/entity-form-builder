import { ENTITIES_JSON_KEY } from '../../constants';
import Preview from '../../components/route-components/preview';

const url = 'http://localhost:8080/api/preview/ddl';

export default function PreviewDDL() {
  return (
    <>
      <h1>DDL generated</h1>
      <Preview storageKey={ENTITIES_JSON_KEY} url={url} />
    </>
  );
}
