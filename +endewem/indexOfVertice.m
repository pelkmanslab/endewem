function index = indexOfVertice(drag, label)

if isnumeric(label)
    % Assume it is an index
    index = label;
    return
end

index = find(strcmp(drag.V, label));
if isempty(index)
    error('indexOfVertice:notFound', ...
          'Failed to find any vertice with this label: %s', label);
end

end

