import { useApi } from '../hooks/use-api';
import ComponentsList from '../components/route-components/components/component-list';
import { ComponentType } from '../types';
import { Link } from 'react-router-dom';

export default function ComponentsListRoute() {
  const { isLoading, error, data } = useApi<Array<ComponentType>>('http://localhost:8080/api/component');

  console.log(isLoading, error, data);
  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!data) return <p>Error: No data</p>;

  return (
    <>
      <ComponentsList components={data} />
      <Link to="/component/new">Add component</Link>
    </>
  );
}
