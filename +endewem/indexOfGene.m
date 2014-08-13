function index = indexOfGene(drag, label)
    
if isnumeric(label)
    % Assume it is an index
    index = label;
    return
end

index = find(strcmp(drag.G, label));
if isempty(index)
    error('indexOfGene:notFound', ...
          'Failed to find any gene with this label: %s', label);
end

end

