import { useEffect, useState } from 'react';
import apiRequest, { HTTPMethod } from '../services/api-service';

export function useApi<T>(url: string): {
  isLoading: boolean;
  data?: T;
  error?: string;
} {
  const [isLoading, setIsLoading] = useState(true);
  const [data, setData] = useState<T>();
  const [error, setError] = useState<string | undefined>(undefined);

  useEffect(() => {
    async function fetchData() {
      try {
        // Fetch the data
        setData(await apiRequest(url, HTTPMethod.GET));
      } catch (e) {
        // setError(e as string);
        console.log(e);
      }
      setIsLoading(false);
    }
    fetchData();
  }, [setData, setError, url]);

  return { isLoading, error, data };
}
