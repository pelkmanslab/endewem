function tests = dragTest()
    tests = functiontests(localfunctions);
end

function testDragBuilding(testCase)
    import endewem.*
    
    drag = newDrag();
    drag.V = {'r', 'v1', 'v2', 'v3', 'v4'};
    drag.r = 1;
    drag.E = [[1 2]; [1 5]]; % the rest connect with the function.
    % Test appending of arcs.
    drag = appendArc(drag, 'v1', 'v3');
    drag = appendArc(drag, 'v1', 'v2');
    expectedEdges =  [[1 2]; [1 5]; [2 4]; [2 3]];
    testCase.verifyEqual(drag.E, expectedEdges, 'Failed to append arcs');
    % Test for no duplicate check.
    try
        drag = appendArc(drag, 'v1', 'v2'); % Expecting exception.
    catch actualME
        testCase.verifyEqual(actualME.identifier, 'addArc:duplicate', ...
            'The function threw an exception with the unexpected identifier.');
    end
    % Define genes.
    drag.G = {'g1', 'g2', 'g3', 'g4'};
    
    % Test adding new maps.
    drag = map(drag, 'v1', 'g2');
    % Test for no duplicate check.
    try
        drag = map(drag, 'v1', 'g2'); % Expecting exception.
    catch actualME
        testCase.verifyEqual(actualME.identifier, 'map:duplicate', ...
            'The function threw an exception with the unexpected identifier.');
    end
    
end
