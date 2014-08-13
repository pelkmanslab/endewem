function drag = newDrag()
%NEWDRAG Builds and returns DRAG skeleton.
% Returns DRAG:
% * drag is an ontology DRAG struct with: 
% ** drag.V - all classificaitons or verticies (cell array of labels),
% ** drag.r - root classification index (index of root in V), usually 1,
% ** drag.E - all dependencies or directed edges (cell array of tuples of 
%             any (v1,v2) if v1 is connected to v2 and are integers or 
%             indexes in V),
% ** drag.G - set of all genes (cell array of labels),
% ** drag.A - is an annotation function, mapping classes in V to subsets 
%             of G (cell array of cell arrays)

drag = struct();
drag.V = {};
drag.r = 1;
drag.E = [];
drag.G = {};   
drag.A = [];

end

