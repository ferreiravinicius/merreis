import { useCallback } from "react";

export interface UseSetterProps<T> {
  action: React.Dispatch<React.SetStateAction<T>>;
  name?: string;
}

const useSetter = <T>(props?: UseSetterProps<T>) => {
  
  const setter = useCallback((value: any): void => {
    if (props) {
      const { action, name } = props;
      if (name) {
        action((actual: any) => ({ ...actual, [name]: value }));
      } else {
        action(value);
      }
    }
  }, [props]);

  return [ setter ];
};

export default useSetter;
